package com.my.projmanager.security.controller;

import com.my.projmanager.controller.EmployeeController;
import com.my.projmanager.controller.request.EmployeeCreateRequest;
import com.my.projmanager.controller.request.EmployeeInviteRequest;
import com.my.projmanager.exceptions.EntityNotFoundException;
import com.my.projmanager.mailing.MailSenderService;
import com.my.projmanager.model.impl.*;
import com.my.projmanager.repository.InvitationRepository;
import com.my.projmanager.security.util.TokenUtils;
import com.my.projmanager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final EmployeeService employeeService;
    private final MailSenderService mailSenderService;
    private final InvitationRepository invitationRepository;
    private final TokenUtils tokenUtils;

    //from SUPER_USER
    //send mail for user
    @PostMapping("/invitation")
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody EmployeeInviteRequest request) throws MessagingException {
        Invitation inviteToken = new Invitation();
        inviteToken.setToken(tokenUtils.generateInviteToken(request));
        invitationRepository.save(inviteToken);

        String content = "http://localhost:8080/registration/confirm?token="
                +inviteToken.getToken();
        mailSenderService.send(request.getMail(), "invitation", content);
    }

    //from user mail
    @PostMapping("/confirm")
    public void confirmRegistration(@RequestParam("token") String inviteToken, @RequestBody EmployeeCreateRequest request) {

        Invitation invitation = invitationRepository.findById(inviteToken).get();
        if (!invitation.getToken().equals(inviteToken)) {
            throw new EntityNotFoundException("Not found in DB");
        }

        invitationRepository.delete(invitation);
        employeeService.save(EmployeeController.fillEmployeeByRequest(new Employee(), request));
    }
}
