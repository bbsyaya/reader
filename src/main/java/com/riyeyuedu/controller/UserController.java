package com.riyeyuedu.controller;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.riyeyuedu.controller.Format.IndexLoginFormat;
import com.riyeyuedu.controller.Format.IndexRegisterFormat;
import com.riyeyuedu.entity.BookCaseEntity;
import com.riyeyuedu.entity.ResponseEntity;
import com.riyeyuedu.entity.UserEntity;
import com.riyeyuedu.service.BookCaseService;
import com.riyeyuedu.service.RedisService;
import com.riyeyuedu.service.UserService;
import com.riyeyuedu.util.JWTUtil;
import com.riyeyuedu.util.SmsUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UserController {
    private UserService userService;

    private RedisService redisService;

    private BookCaseService bookCaseService;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setBookCaseService(BookCaseService bookCaseService) {
        this.bookCaseService = bookCaseService;
    }

    @RequestMapping(value = "/checkReader", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity checkReader(@RequestBody IndexRegisterFormat format) {
        if (userService.getUserByPhone(format.getPhone()) != null) {
            return new ResponseEntity(200, "用户存在", null);
        } else {
            return new ResponseEntity(400, "用户不存在", null);
        }
    }

    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity changePassword(@RequestBody IndexRegisterFormat format) {
        if (userService.getUserByPhone(format.getPhone()) != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setPhone(format.getPhone());
            userEntity.setPassword(format.getPassword());
            Boolean isChange = userService.changePassword(userEntity);
            return new ResponseEntity(isChange ? 200 : 400, "", isChange);
        } else {
            return new ResponseEntity(400, "修改失败", null);
        }
    }

    @RequestMapping(value = "/user/editAvatar/{uid}", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity editAvatar(@RequestBody MultipartFile file, @PathVariable int uid) {
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        String avatar = df.format(day) + "." + suffix;

        String portrait = userService.getPortraitByUid(uid);

        String oldAvatar = portrait.substring(portrait.lastIndexOf("/") + 1);
        try {
            ClientConfiguration config = new ClientConfiguration();
            config.setConnectionTimeout(1000);
            config.setMaxErrorRetry(1);

            // endpoint以杭州为例，其它region请按实际情况填写
            String endpoint = "http://oss-cn-beijing.aliyuncs.com";
            // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
            String accessKeyId = "LTAICBaKv7FrgzZf";
            String accessKeySecret = "0CWhs6GjKdOqhGWNxZod3jM1npFdE7";
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流
            InputStream inputStream = file.getInputStream();

            ossClient.putObject("nealcaffrey", "avatar/" + avatar, inputStream);

            if(!portrait.contains("default")) {
                // 删除Object
                ossClient.deleteObject("nealcaffrey", "avatar/" + oldAvatar);
            }

            ossClient.shutdown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // 关闭client

        avatar = "http://nealcaffrey.oss-cn-beijing.aliyuncs.com/avatar/" + avatar;

        UserEntity userEntity = new UserEntity();
        userEntity.setPortrait(avatar);
        userEntity.setUid(uid);

        userService.updateAvatar(userEntity);
        return new ResponseEntity(avatar);
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    @CrossOrigin
    @RequiresAuthentication
    public ResponseEntity getPerson() {
        return new ResponseEntity(200, "you coming", null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin
    public Map login(@RequestBody IndexLoginFormat format) {
        UserEntity userEntity;
        if (format.getUsername().length() != 11) {
            userEntity = userService.getReaderByName(format.getUsername());
        } else {
            userEntity = userService.getUserByPhone(format.getUsername());
        }

        if (userEntity != null) {
            if (userEntity.getPassword().equals(format.getPassword())) {
                Map<String, String> map = new HashMap<>();
                map.put("msg", "Login success");
                map.put("code", "200");
                map.put("token", JWTUtil.sign(userEntity.getUsername(), userEntity.getPassword()));
                map.put("uid", String.valueOf(userEntity.getUid()));
                map.put("username", userEntity.getUsername());
                return map;
            } else {
                throw new UnauthorizedException();
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity checkPhone(@RequestBody IndexRegisterFormat format) {
        if (userService.getUserByPhone(format.getPhone()) == null) {
            return new ResponseEntity("手机号可用");
        } else {
            return new ResponseEntity(400, "手机号已被注册", "null");
        }
    }

    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity checkUsername(@RequestBody IndexRegisterFormat format) {
        if (userService.getUserByUsername(format.getUsername()) == null) {
            return new ResponseEntity("用户名可用");
        }else {
            return new ResponseEntity(400, "用户名已被注册", "null");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity register(@RequestBody IndexRegisterFormat format) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(format.getUsername());
        userEntity.setPassword(format.getPassword());
        userEntity.setPortrait("http://nealcaffrey.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
        userEntity.setPhone(format.getPhone());

        userService.register(userEntity);

        /*新增，待测试*/
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setUid(userService.getUserByPhone(userEntity.getPhone()).getUid());
        bookCaseEntity.setType("默认分组");
        bookCaseService.addBookCase(bookCaseEntity);

        return new ResponseEntity("注册成功");
    }

    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity getCode(@RequestBody IndexRegisterFormat format) {
        //产生随机数
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result += random.nextInt(10);
        }

        setCode(result);

        if (!redisService.exists(format.getPhone())) {
            redisService.set(format.getPhone(), true, 60L);
            return SmsUtil.sendSms(result, format.getPhone());
        } else {
            return new ResponseEntity(400, "请求过于频繁，请稍后再试", null);
        }
    }

    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity checkCode(@RequestBody IndexRegisterFormat format) {
        if (getCode().equals(format.getCode())) {
            return new ResponseEntity("验证码正确");
        } else {
            return new ResponseEntity(400, "验证码输入错误", null);
        }
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity updateInfo(@RequestBody UserEntity userEntity) {
        return new ResponseEntity(userService.updateInfo(userEntity));
    }

    @RequestMapping(value = "/user/info/{uid}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getUserInfo(@PathVariable int uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("info", userService.getInfoByUid(uid));
        map.put("bookNum", bookCaseService.getBookNumByUid(uid));
        return new ResponseEntity(map);
    }

    @RequestMapping(value = "/user/portrait/{uid}")
    @CrossOrigin
    public ResponseEntity getUserPortrait(@PathVariable int uid) {
        return new ResponseEntity(userService.getPortraitByUid(uid));
    }

    @RequestMapping(value = "/otherUser/{uid}")
    @CrossOrigin
    public ResponseEntity getOtherInfo(@PathVariable int uid) {
        return new ResponseEntity(userService.getInfoByUid(uid));
    }
}
