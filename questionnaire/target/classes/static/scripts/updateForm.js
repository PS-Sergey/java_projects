function addQuestion() {
    const cloneQuestion = cloneQuestionPat.cloneNode(true)
    cloneQuestion.querySelector('input[name=addAnswer]').addEventListener('click', addAnswer)
    cloneQuestion.querySelector('input[name=delQuestin]').addEventListener('click', deleteQuestion)
    const cloneDelAnswerButtons = cloneQuestion.querySelectorAll('input[name=delAnswer]')
    for (let i = 0; i < cloneDelAnswerButtons.length; i++) {
        cloneDelAnswerButtons[i].addEventListener('click', deleteAnswer)
    }
    applicationForm.append(cloneQuestion)
    applicationForm.append(document.createElement('br'))
}

function addAnswer() {
    const cloneAnswer = cloneAnswerPat.cloneNode(true)
    cloneAnswer.querySelector('input[name=delAnswer]').addEventListener('click', deleteAnswer)
    this.parentNode.append(cloneAnswer)
}

function deleteQuestion() {
    this.parentNode.remove()
}

function deleteAnswer() {
    this.parentNode.remove();
}

function addBr() {
    const questions = document.querySelectorAll('.question')
    for (let i = 0; i < questions.length; i++) {
        questions[i].after(document.createElement('br'));
    }
}

window.onload = addBr;

const applicationForm = document.querySelector('.ApplicationForm')

const question = document.querySelector('.question')
const cloneQuestionPat = question.cloneNode(true)
cloneQuestionPat.querySelector('textarea[name=questionText]').value = ''
cloneQuestionPat.removeAttribute('id')
let cloneQuestionPatAnswers = cloneQuestionPat.querySelectorAll('.answer')
for (let i = 0; i < cloneQuestionPatAnswers.length; i++) {
    if (i < 2) {
        cloneQuestionPatAnswers[i].removeAttribute('id')
        cloneQuestionPatAnswers[i].querySelector('textarea[name=answerText]').value = ''
        cloneQuestionPatAnswers[i].querySelector('input[name=answerCheck]').checked = false
    } else {
        cloneQuestionPatAnswers[i].remove()
    }
}


const answer = document.querySelector('.answer')
const cloneAnswerPat = answer.cloneNode(true)
cloneAnswerPat.removeAttribute('id')
cloneAnswerPat.querySelector('textarea[name=answerText]').value = ''
cloneAnswerPat.querySelector('input[name=answerCheck]').checked = false

const questionButton = document.querySelector('#addQuestion')

const answerButtons = document.querySelectorAll('input[name=addAnswer]')
for (let i = 0; i < answerButtons.length; i++) {
    answerButtons[i].addEventListener('click', addAnswer)
}

const delQuestionButtons = document.querySelectorAll('input[name=delQuestin]')
for (let i = 0; i < delQuestionButtons.length; i++) {
    delQuestionButtons[i].addEventListener('click', deleteQuestion)
}

questionButton.addEventListener('click', addQuestion)

const delAnswerButtons = applicationForm.querySelectorAll('input[name=delAnswer]')
for (let i = 0; i < delAnswerButtons.length; i++) {
    delAnswerButtons[i].addEventListener('click', deleteAnswer)
}
const id = applicationForm.getAttribute('id').split('=')[1]
const delApplicationForm = document.querySelector('#delApplicationForm')

function jsonBuilder() {
    const applicationForm = new Object();
    const app = document.querySelector('.ApplicationForm')
    applicationForm.id = Number(app.getAttribute('id').split('=')[1])
    applicationForm.title = app.querySelector('input[name=title]').value

    const questions = app.querySelectorAll('.question')
    const arrQuestions = [];
    if (questions.length < 1) {
        throw new Error('Анкета должна содержать хотя бы один вопрос')
    }
    for (let i = 0; i < questions.length; i++) {
        const question = new Object()
        if (questions[i].hasAttribute('id')) {
            question.id = Number(questions[i].getAttribute('id').split('=')[1])
        }
        question.questionText = questions[i].querySelector('textarea[name=questionText]').value
        if (question.questionText == '') {
            throw new Error('Заполнены не все поля')
        }

        const answers = questions[i].querySelectorAll('.answer')
        const arrAnswers = [];
        if (answers.length < 2) {
            throw new Error('Вопрос должен содержать минимум 2 ответа')
        }
        for (let j = 0; j < answers.length; j++) {
            const answer = new Object();
            if (answers[j].hasAttribute('id')) {
                answer.id = Number(answers[j].getAttribute('id').split('=')[1])
            }
            answer.answerText = answers[j].querySelector('textarea[name=answerText]').value
            if (answer.answerText == '') {
                throw new Error('Заполнены не все поля')
            }
            answer.answerCheck = answers[j].querySelector('input[name=answerCheck]').checked
            arrAnswers.push(answer)
        }
        question.answers = arrAnswers
        arrQuestions.push(question)
    }
    applicationForm.questions = arrQuestions
    const json = JSON.stringify(applicationForm)
    return json
}

$('#ApplicationForm').submit(function() {
    try {
        $.ajax({
            url: 'http://localhost:8080/questionnaire/updateApplication/' + id,
            type: 'PUT',
            data: jsonBuilder(),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (response) {
                alert(response.message)
                window.location.href = 'http://localhost:8080/questionnaire/forms';
            },
            error: function (response) {
                alert('error: ' + response.message)
            }
        })
        return false
    } catch (ex) {
        alert(ex.message)
        return false
    }
})

delApplicationForm.addEventListener('click', () => {
    $.ajax({
                url: 'http://localhost:8080/questionnaire/delApplication/' + id,
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: false,
                success: function (response) {
                    alert(response.message)
                    window.location.href = 'http://localhost:8080/questionnaire/forms'
                },
                error: function (response){
                    alert('error: ' + response.message)
                }
            })
    return false
    }
)
