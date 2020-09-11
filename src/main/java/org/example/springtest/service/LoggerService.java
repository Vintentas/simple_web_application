package org.example.springtest.service;

import org.example.springtest.dto.LoggerDTO;
import org.example.springtest.dto.UserChangesLog;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface LoggerService {
    public Iterable<LoggerDTO> findAllLogs();
    public Boolean saveLog(UserChangesLog log);
}
