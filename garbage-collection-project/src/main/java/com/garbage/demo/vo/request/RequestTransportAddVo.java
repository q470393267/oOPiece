package com.garbage.demo.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class RequestTransportAddVo {

    private Integer type;
    /**
     * 传输列表
     */
    private String garbageIds;

    /**
     * 目的地
     */
    private String dest;

    /**
     * 运出时间
     */
    private LocalDate outTime;
    public ArrayList<String> getGarbageIds(){
        char[] arr = garbageIds.toCharArray();
        ArrayList<String> ids = new ArrayList<>();
        String str = "";
        for (int i =0;i<arr.length;i++){
            if(arr[i]==','&&i!=0){
                ids.add(str);
                str = "";
            }else{
                str+= arr[i];
            }
        }

        ids.add(str);
        return ids;
    }
}
