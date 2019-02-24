package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private long id;
    private long userId;
    private String userName;
    private long friendId;
    private String friendName;
    private String sms;
    private Date smsDate;
    private String file;

}
