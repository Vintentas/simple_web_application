package org.example.springtest.service;

import lombok.RequiredArgsConstructor;
import org.example.springtest.dao.mybatis.UserLoggerMapper;
import org.example.springtest.dto.LoggerDTO;
import org.example.springtest.dto.UserChangesLog;
import org.example.springtest.entities.UserLogger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LoggerServiceImpl implements LoggerService {

    final UserLoggerMapper loggerRepository;
    final UserService userService;


    @Override
    public List<LoggerDTO> findAllLogs() {
        List<LoggerDTO> logsDTO = new ArrayList<>();
        List<UserLogger> allLogs = loggerRepository.findAll();
        for (UserLogger log:allLogs){
            LoggerDTO loggerDTO=LoggerDTO.builder()
                    .id(log.getId())
                    .changedUserId(log.getChangedUserId())
                    .changerId(log.getChangerId())
                    .message(log.getMessage())
                    .changes(log.getChanges())
                    .build();
            loggerDTO.setChangedUserLogin(userService.findUserById(log.getChangedUserId()).getEmail());
            loggerDTO.setChangerLogin(userService.findUserById(log.getChangerId()).getEmail());
            loggerDTO.setDate(log.getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
            logsDTO.add(loggerDTO);
        }
        return logsDTO;
    }

    @Override
    public Boolean saveLog(UserChangesLog log) {
        String shortMessage = log.getSignature();
        UserLogger daoLog;
        if ("UserMapper.changePassword(..)".equals(shortMessage)) {
            daoLog = parseUserChangePassword(log);
        } else if ("UserMapper.updateUser(..)".equals(shortMessage)) {
            daoLog = parseUserUpdate(log);
        } else {
            daoLog = parseUserSave(log);
        }
        return loggerRepository.save(daoLog);
    }

    private UserLogger parseUserUpdate(UserChangesLog log) {
        int changedUserId = parseArgsMailToId(log);
        int changerId = userService.findUserByMail(log.getChangerLogin()).getId();
        log.setSignature("User id: " +changerId+", has updated User with id:"+changedUserId);
        return defaultBuild(log, changerId, changedUserId);
    }

    private UserLogger parseUserChangePassword(UserChangesLog log) {
        int changerId = userService.findUserByMail(log.getChangerLogin()).getId();
        log.setSignature("User id: " + changerId + ", has changed his password");
        log.setArguments(new String[]{"Not logging Information"});
        return defaultBuild(log, changerId, changerId);
    }

    private UserLogger parseUserSave(UserChangesLog log) {
        int changedUserId = parseArgsMailToId(log);
        int changerId = changedUserId;
        if (!"anonymousUser".equals(log.getChangerLogin())) {
            changerId = userService.findUserByMail(log.getChangerLogin()).getId();
        }
        log.setSignature("User id: " +changerId+", has created User with id:"+changedUserId);
        return defaultBuild(log, changerId, changedUserId);
    }

    private Integer parseArgsMailToId(UserChangesLog log) {
        Object[] arguments = log.getArguments();
        String args = arguments[0].toString();
        args = args.subSequence(5, args.length()-1).toString();
        String[] splitedArgs = args.split(", ");
        String email = splitedArgs[3].replaceFirst("email=", "");
        splitedArgs[4]="";
        log.setArguments(splitedArgs);
        if ("null".equals(email)) {
            return Integer.valueOf(splitedArgs[0].replaceFirst("id=", ""));
        }
        return userService.findUserByMail(email).getId();
    }


    private UserLogger defaultBuild(UserChangesLog log, int changerId, int changedUserId) {
        return UserLogger.builder()
                .changes(Arrays.toString(log.getArguments()))
                .changerId(changerId)
                .changedUserId(changedUserId)
                .message(log.getSignature())
                .date(LocalDateTime.now())
                .build();
    }
}


