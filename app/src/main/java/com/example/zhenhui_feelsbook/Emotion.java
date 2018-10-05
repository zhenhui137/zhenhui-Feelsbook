/* This Emotion calsee is modified based on the tweet class in lonelyTweeter */

package com.example.zhenhui_feelsbook;

import java.sql.Time;
import java.util.Date;

public class Emotion {
    protected String emotion;
    protected String comment;
    protected Date time;

    public void setEmotion(String emotion){
        this.emotion = emotion;
    };

    public void setComment(String comment) throws TooLongCommentException{
        if(comment.length()>100){
            throw new TooLongCommentException();
        }
        this.comment = comment;
    }

    public void setTime(Date date){
        this.time = date;
    }

    public String getEmotion(){
        return this.emotion;
    }

    public String getComment(){
        return this.comment;
    }
    public Date getTime(){
        return this.time;
    }

}
