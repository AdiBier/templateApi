package com.ab.templateApi.view;

public class UserView {
    public interface WithoutRelations {}
    public interface UserDto extends WithoutRelations, WithUserId {}
    public interface WithRelations extends WithoutRelations, WithUserId {} //add relations
    public interface WithPassword extends WithoutRelations, WithUserId {}
    public interface WithUserId {}
}
