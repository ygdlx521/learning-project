<template>
    <div>
        <el-form ref="loginForm" :rules="rules" :model="loginForm" class="loginContainer">
            <h3 class="loginTitle">系统登录</h3>
            <el-form-item prop="username">
                <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="text" v-model="loginForm.password" auto-complete="off" placeholder="请输入用户密码"></el-input>
            </el-form-item>
            <el-checkbox class="loginRemember" v-model="checked">是否记住用户名与密码</el-checkbox>
            <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data(){
            return {
                loginForm: {
                    username: "daliang",
                    password: "123456"
                },
                checked: true,
                rules:{
                    username:[
                        {
                            required: true,
                            message: "请输入用户名",
                            trigger: "blur"
                        }
                    ],
                    password:[
                        {
                            required: true,
                            message: "请输入密码",
                            trigger: "blur"
                        }
                    ]
                }
            }
        },
        methods:{
            submitLogin(){
                this.$refs["loginForm"].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        this.$message.error('用户名或密码不全');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }

    .loginRemember {
        text-align: left;
        margin: 0px 0px 15px 0px;
    }
</style>