/* modified based on TooLongTweetException in longlyTweeter*/

package com.example.zhenhui_feelsbook;

public class TooLongCommentException extends Exception {
    TooLongCommentException(){
        super("This message is longer than 100, please keep it short");
    }
}
