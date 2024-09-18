package com.connectCare.connectCareApi.exceptions;

import java.time.LocalDate;
import java.time.LocalTime;

public class HorarioAgendadoException extends RuntimeException{

    public HorarioAgendadoException(LocalDate dia, LocalTime hora) {
        super("O horário das " + hora + " do dia " + dia + " já está agendado.");
    }
}
