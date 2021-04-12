## 코드 공유 사이트(code02)

### 소개
코드를 서로 공유해 리뷰해주고 궁금한 것이 있으면 질문하고 대답해주는 사이트입니다.

## 기술 스택

- Front-End
  - HTML, CSS, JavaScript
  - JQuery
  - BootStrap
- Back-End
  - Java
  - Mybatis
- WebServer
  - Apache-Tomcat 9.0
- DataBase
  - Oracle 11g
- Framework
  - Spring

## 프로젝트 기능 및 코드 설명
* ### 회원가입 기능
<img width='300px' heigh='400px' src='https://user-images.githubusercontent.com/81149759/114335306-758d0b00-9b87-11eb-9b74-515651892571.PNG'><img width='300px' height='400px' src='https://user-images.githubusercontent.com/81149759/114335309-76be3800-9b87-11eb-85a2-8c9be7cc6dd8.PNG'>

```javascript
$.ajax({
			type:"get",
			async:true,
			url:"/code02/member/duplicateId",
			dataType:"text",
			data:{id: _id},
			success:function (data, textStatus) {
				if(data == 'usable') {
					$('#messageId').text("사용할 수 있는 아이디입니다.");
					okId = 'yes';
				} else {
					$('#messageId').text("이미 사용 중인 아이디입니다.");
					okId = 'no';
				}
			},
			error:function (data,textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete:function (data,textStatus) {
				
			}
		});
```
```javascript
$(document).on("keyup", "#id", function() { 
		$(this).val( $(this).val().replace(/[^a-zA-Z0-9]/g, "")); 
	});
	
	$(document).on("keyup", "#phone", function() { 
		$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
	});
	
	$(document).on("keyup", "#birthDay", function() { 
		$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/([0-9]{4})([0-9]{2})([0-9]{2})$/,"$1-$2-$3")); 
	});

```
ajax를 이용해 아이디와 이메일, 전화번호의 중복체크 관리를 하였습니다.
정규 표현식을 이용해 아이디를 영어와 숫자로, 전화번호와 생년월일의 입력을 제한하였습니다.

* ### Interceptor
```xml
<interceptors>
        <interceptor>
            <mapping path="/board/**"/>
            <mapping path="/reply/**"/>
            <mapping path="/member/myPage"/>
            <exclude-mapping path="/board/listArticles"/>
            <exclude-mapping path="/board/listArticles"/>
            <beans:ref bean="MemberInterceptor"/>
        </interceptor>
    </interceptors>
```
```java
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
  response.setContentType("text/html;charset=utf-8");
  boolean loginFlag = false;
		   
  HttpSession session = request.getSession();
	    
  LoginVO loginVO = (LoginVO) session.getAttribute("loginInfo");
	    
  if(loginVO != null ){
          
    loginFlag = true;
       
  } else {
      
    PrintWriter out = response.getWriter();
    out.println("<script>alert('로그인을 해주세요');history.go(-1);</script>");
    out.flush();
    ...
  }
}
...
```
Interceptor를 이용하여 페이지에 대한 권한을 설정해주었습니다.
