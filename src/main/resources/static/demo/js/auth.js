function preAuth() {
    var username = localStorage.getItem("username")
    var password = localStorage.getItem("password")

    if (username != null && password != null) {
        doAuth(username, password)
    } else {
        fillEmptyData()
    }

}

function fillData() {
    $('#login_view').show()
    $('#no_login_view').hide()
    $('#home_username').html(localStorage.getItem("username"))
}

function fillEmptyData() {
    $('#login_view').hide()
    $('#no_login_view').show()
}

function doAuth(username, password) {
    $.ajax({
        type: 'POST',
        url: 'http://127.0.0.1/user/auth',
        data: {
            'username': username,
            'password': password
        },
        dataType: 'json',
        success: function(data) {
            if (data.code === 0) {
                localStorage.setItem('uid', data.data.id)
                localStorage.setItem("username", username)
                localStorage.setItem("password", password)
                fillData()
            } else {
                alert('身份认证错误，请重新登录')
                remove()
                location.href = 'login.html'
            }
        },
        error: function () {
            fillEmptyData()
        }

    })
}

function login(username, password) {
    $.ajax({
        type: 'POST',
        url: 'http://127.0.0.1/user/auth',
        data: {
            'username': username,
            'password': password
        },
        dataType: 'json',
        success: function(data) {
            alert(data.message)
            if (data.code === 0) {
                localStorage.setItem('uid', data.data.id)
                localStorage.setItem("username", username)
                localStorage.setItem("password", password)
                location.href = 'index.html'
            }
        },
        error: function (data) {
            alert('err ' + JSON.stringify(data[0]))
        }

    })
}

function register(username, password, password2) {
    if (password !== password2) {
        alert('两次输入的密码不一致')
    } else {
        $.ajax({
            type: 'POST',
            url: 'http://127.0.0.1/user/register',
            data: {
                'username': username,
                'password': password
            },
            dataType: 'json',
            success: function(data) {
                alert(data.message)
                if (data.code === 0) {
                    localStorage.setItem('uid', data.data.id)
                    localStorage.setItem("username", username)
                    localStorage.setItem("password", password)
                    location.href = 'index.html'
                }
            },
            error: function (data) {

            }

        })
    }
}

function remove() {
    localStorage.removeItem('uid')
    localStorage.removeItem('username')
    localStorage.removeItem('password')
}

function logout() {
    remove()
    alert('注销成功')
    location.href = 'index.html'
}