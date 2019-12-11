package online.daliang.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringMVCController {

    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("Hello SpringMVC!");
        return "success";
    }

    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "age") Integer age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    /**
     * 重定向
     *
     */
    @RequestMapping(value="/testRedirect")
    public String testRedirect() {
        // 重定向到 ok.jsp

        return "redirect:ok.jsp";

        //转发
        //return "forward:/WEB-INF/views/success.jsp";
    }

    /**
     * 处理响应数据
     *
     * JavaWEB: request.setAttribute("username","Tom");
     * 			request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request,response);
     *
     * 框架:
     * 1. ModelAndView
     * 	  将方法的返回值修改为ModelAndView类型，在方法中最终返回一个ModelAndView对象
     * 2. Map  /  Model
     * 	 在方法的形参中直接声明一个Map或者是Model类型的行参.
     */
    @RequestMapping(value="/testResponseData")
    public String  testResponseData(/*Map<String,Object> map  */ Model model   ) {
        //响应数据   username = Tom

        //Map
        //map.put("username", "Tom");


        //Model
        model.addAttribute("username", "Jerry");

        return "success";
    }

    /**
     @RequestMapping(value="/testResponseData")
     public ModelAndView testResponseData() {
     //响应数据   username = Tom
     ModelAndView  mav = new ModelAndView();

     //设置响应数据
     mav.addObject("username", "Tom"); //将来在底层会把数据放到Request对象中。

     //设置视图信息
     mav.setViewName("success");

     return mav ;
     }
     */

    /**
     * REST
     *
     * 占位符:  {占位符的名字}
     *
     * 浏览器端的请求: /testRest/dahai/44
     *
     * @PathVariable:  将占位符对应的URL中具体的值映射到请求处理方法的形参中。
     *
     */
    @RequestMapping(value="/testRest/{username}/{age}")
    public String testRest(@PathVariable("username")String username , @PathVariable("age") Integer age ) {

        System.out.println("username : " + username );

        System.out.println("age : " +age );
        return "success";
    }


}
