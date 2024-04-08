/**
 * 비밀번호 찾기 변수
 * 
 * @Class   SearchUserPwVo
 * @Author  Koo Bon Sang
 * @Date    2024.04.086
 * @Version 1.1.0
 * 
 */

export class SearchUserPwVo {

    private userName: string = ''   // 사용자명
    private email: string = ''      // 이메일
    private userId: string = ''     // 아이디

    // userName getter
    getUserName(): string {
        return this.userName;
    }
    // userName setter
    setUserName(userName: string) {
        this.userName = userName;
    }
    // email getter
    getEmail(): string {
        return this.email;
    }
    // email setter
    setEmail(email: string) {
        this.email = email;
    }
    // userId getter
    getUserId(): string {
        return this.userId;
    }
    // userId setter
    setUserId(userId: string) {
        this.userId = userId;
    }
}