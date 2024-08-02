package uluru.uluruspringbackend.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uluru.uluruspringbackend.data.dto.member.MemberSignup1PageDTO;
import uluru.uluruspringbackend.data.dto.member.MemberSignup2PageDTO;
import uluru.uluruspringbackend.data.dto.member.MemberSignup3PageDTO;
import uluru.uluruspringbackend.service.MemberService;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
public class MemberSignupController {

    private final MemberService memberService;


    @PostMapping("/page-1")
    public String saveInfoPage1(@RequestBody MemberSignup1PageDTO memberSignup1PageDTO, HttpServletResponse response){
        //db저장
        memberService.addInfo(memberSignup1PageDTO);

        response.setHeader("Custom-Header", memberSignup1PageDTO.getEmail());
        return "redirect:http://localhost:3000/entering-page-2";

    }


    @PostMapping("/page-2")
    public String saveInfoPage2(@RequestBody MemberSignup2PageDTO memberSignup2PageDTO,HttpServletResponse response){
        //db저장

        memberService.addInfo(memberSignup2PageDTO);
        response.setHeader("Custom-Header", memberSignup2PageDTO.getEmail());

        return "redirect:http://localhost:3000/entering-page-3";

    }


    @PostMapping("/page-3")
    public String saveInfoPage3(@RequestBody MemberSignup3PageDTO memberSignup3PageDTO,HttpServletResponse response){
        //db저장
        memberService.addInfo(memberSignup3PageDTO);
        response.setHeader("Custom-Header", memberSignup3PageDTO.getEmail());

        return "redirect:http://localhost:3000/home";

    }

}
