package com.example.week05_inclass;

public interface FragmentCallBacks {
    public void onMsgFromMainToFragment(String id, String fullName, String classId, String point,int position);
    public void onMsgFromMainToFragment(String sender);

}
