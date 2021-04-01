package com.wanghang.code.design.build.two;




public class User {
    private final String firstName; // 必须
    private final String lastName; // 必须
    private final int age; // 可选
    private final String phone; // 可选
    private final String address; // 可选


    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }
    @Override
    public String toString() {
        return "User: " + this.firstName + ", " + this.lastName + ", " + this.age + ", " + this.phone + ", " + this.address;
    }


    public static class UserBuilder {
        private final String firstName;  //必须
        private final String lastName;   //必须
        private int age;
        private String phone;
        private String address;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        // 返回最终构造的用户对象
        public User build() {
            User user = new User(this);
            validateUserObject(user);
            return user;
        }

        //todo：自定义的逻辑,当然也是可以已没有的
        private void validateUserObject(User user) {
            // 基本的验证检查
            // 确保用户对象不违反系统假设
        }
    }
}
