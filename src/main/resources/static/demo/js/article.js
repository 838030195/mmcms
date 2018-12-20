var url = window.location.origin

function submit() {
    var title = document.getElementById('title').value
    var subtitle = document.getElementById('subtitle').value
    var content = document.getElementById('result').innerHTML
    var source = document.getElementById('oriContent').value
    var uid = localStorage.getItem('uid')


    $.ajax({
        type: 'POST',
        url: url + '/article/add',
        dataType: 'json',
        data: {
            "title": title,
            "subtitle": subtitle,
            "content": content,
            "authorId": uid,
            "source": source
        },
        success: function (data) {
            if (data.code === 0) {
                alert('发表成功')
                location.href = url + '/info/' + data.data.id
            } else {
                alert('发表失败')
            }
        },
        error: function () {
            alert('发布失败')
        }
    })
}

function update() {
    var title = document.getElementById('title').value
    var subtitle = document.getElementById('subtitle').value
    var content = document.getElementById('result').innerHTML
    var source = document.getElementById('oriContent').value
    var id = document.getElementById('id').innerHTML

    $.ajax({
        type: 'POST',
        url: url + '/article/update',
        dataType: 'json',
        data: {
            "id": id,
            "title": title,
            "subtitle": subtitle,
            "content": content,
            "source": source
        },
        success: function (data) {
            if (data.code === 0) {
                alert('发表成功')
                location.href = url + '/info/' + data.data.id
            } else {
                alert('发表失败')
            }
        },
        error: function () {
            alert('发布失败')
        }
    })
}

function loadList() {
    $.ajax({
        type: 'POST',
        url: url + '/article/list',
        dataType: 'json',
        success: function (data) {
            var list = data.data

            var table = document.getElementById('article_table')
            table.innerHTML = ''

            for (var i = 0; i < list.length; i++) {
                var tr = document.createElement('tr')
                var tdId = document.createElement('td')
                tdId.style.verticalAlign = 'middle'
                tdId.innerHTML = list[i].id
                tr.appendChild(tdId)

                var tdTitle = document.createElement('td')
                tdTitle.style.verticalAlign = 'middle'
                tdTitle.innerHTML = '<a href=' + url + '/info/' + list[i].id + '>' + list[i].title + '</a>'
                tr.appendChild(tdTitle)

                var tdTime = document.createElement('td')
                tdTime.style.verticalAlign = 'middle'
                tdTime.innerHTML = timestampToTime(list[i].time)
                tr.appendChild(tdTime)

                var tdAuthor = document.createElement('td')
                tdAuthor.style.verticalAlign = 'middle'
                tdAuthor.innerHTML = list[i].author
                tr.appendChild(tdAuthor)

                var tdAction = document.createElement('td')
                tdAction.style.verticalAlign = 'middle'
                var id = list[i].id
                tdAction.innerHTML = '<button type="button" class="btn btn-danger" onclick="del(' + id + ')">删除</button>'
                tr.appendChild(tdAction)

                table.appendChild(tr)
            }
        }
    })
}

function timestampToTime(timestamp) {
    var date = new Date(timestamp);
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    var h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
    var m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
    var s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
    return Y+M+D+h+m+s;
}

function del(id) {
    $.ajax({
        type: 'POST',
        url: url + '/article/remove',
        dataType: 'json',
        data: {
            "id": id
        },
        success: function (data) {
            alert(data.message)
            loadList()
        },
        error: function () {
            alert('删除失败')
        }
    })
}