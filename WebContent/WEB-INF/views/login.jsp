<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="mainWrapper">
	<div class="login-container">
		<div class="login-card">
			<div class="login-form">
				<c:url var="loginUrl" value="/login" />
				<form action="${loginUrl}" method="post" class="form-horizontal"
					name="login" id="login">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							<p>Invalid username and password.</p>
						</div>
					</c:if>
					<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
					</c:if>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="username"><i
							class="fa fa-user"></i></label> <input type="text" class="form-control"
							id="username" name="ssoId" placeholder="Enter Username" required>
					</div>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="password"><i
							class="fa fa-lock"></i></label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Enter Password" required>
					</div>
					<div class="input-group input-sm">
						<span class="captchas"> <img src="Captcha.jpg"
							style="width: 160px; height: 45px;" />
						</span>
						<button type="button" class="btn btn-refresh btn-success"
							onclick="generateCaptcha();" style="margin-left: 25px;">Refresh</button>
					</div>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="username"></label> <input
							type="text" class="form-control" id="captcha" name="captcha"
							placeholder="Enter captcha" required>
					</div>
					<div class="input-group input-sm">
						<div class="checkbox">
							<label><input type="checkbox" id="rememberme"
								name="remember-me"> Remember Me</label>
						</div>
					</div>



					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<div class="form-actions">
						<input type="submit" class="btn btn-block btn-primary btn-default"
							value="Log in" onclick="encryptPwd();">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function encryptPwd() {
		var password = document.getElementById("password").value;
		var hash = sha256(password);
		document.getElementById("password").value = hash;
		return true;
	}
	function generateCaptcha() {
		d = new Date();
		$.ajax({
			url : "Captcha.jpg",
			success : function(data) {
				$(".captchas img").remove();
				var img = "Captcha.jpg";
				$('<img />').attr('src', img + "?" + d.getTime()).attr('title',
						"captcha").attr('alt', "captch").width('160').height(
						'45').appendTo($('.captchas'));
			}
		});
	}
</script>
