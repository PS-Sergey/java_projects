function jsonBuilder() {
    const userApplicationForm = new Object();
    const app = document.querySelector('.ApplicationForm')
    userApplicationForm.applicationFormId = Number(app.getAttribute('id').split('=')[1])

    const userAnswers = app.querySelectorAll('.answer')
    const arrUserAnswers = [];
    for (let i = 0; i < userAnswers.length; i++) {
        const userAnswer = new Object();
        userAnswer.answerId = Number(userAnswers[i].getAttribute('id').split('=')[1])
        userAnswer.userAnswerCheck = userAnswers[i].querySelector('input[name=answerCheck]').checked
        arrUserAnswers.push(userAnswer)
    }
    userApplicationForm.userAnswers = arrUserAnswers
    const json = JSON.stringify(userApplicationForm)
    console.log(json)
   return json
}


$('#ApplicationForm').submit(function(e) {
    $.ajax({
        url: 'http://localhost:8080/questionnaire/applications/saveUserAnswers/',
        type: 'POST',
        data: jsonBuilder(),
        contentType: 'application/json; charset=utf-8',
        dataType: 'text',
        async: false,
        success: function (msg) {
            window.location.href = 'http://localhost:8080/questionnaire/applications/forms';
        },
        error: function (response) {
            alert('error: ' + response.message)
        }
        })
        return false
})