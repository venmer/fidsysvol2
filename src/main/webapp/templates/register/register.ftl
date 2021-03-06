<form class="form register-form" role="form" action="/auth/register" method="post">
    <div class="modal-body">
        <div class="form-group">
            <label for="name">Introduce yourself</label>
            <input type="text" class="form-control" id="name" name="name"
                   placeholder="Please, enter your real name" required>
        </div>
        <div class="form-group" id="register-login-box">
            <label for="login">Login</label>
            <input type="text" class="form-control" id="login" name="login"
                   placeholder="Login" required>
        </div>
        <div class="form-group">
            <label for="email">email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
        </div>
        <div class="form-group">
            <label for="register-pass"">Password</label>
            <input type="password" class="form-control" id="register-pass" name="password"
                   placeholder="Password" required>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary pull-right" id="register-submit">Register</button>
    </div>
</form>