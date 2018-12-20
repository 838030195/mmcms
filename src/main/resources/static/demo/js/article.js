function submit() {
    var title = document.getElementById('title').value
    var subtitle = document.getElementById('title').value
    var content = document.getElementById('result').innerHTML
    var uid = localStorage.getItem('uid')

    $.ajax({
        type: 'POST',
        url: 'http://localhost/article/add',
        dataType: 'json',
        data: {
            "title": title,
            "subtitle": subtitle,
            "content": content,
            "authorId": uid
        },
        success: function (data) {

        },
        error: function (data) {
            alert('发布失败')
        }
    })
}