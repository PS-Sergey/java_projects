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

const applicationForm = document.querySelector('.ApplicationForm')

const question = document.querySelector('.question')
const cloneQuestionPat = question.cloneNode(true)

const answer = document.querySelector('.answer')
const cloneAnswerPat = answer.cloneNode(true)


const questionButton = document.querySelector('#addQuestion')
const answerButton = document.querySelector('input[name=addAnswer]')
const saveButton = document.querySelector('#save')
const delQuestionButton = document.querySelector('input[name=delQuestin]')

questionButton.addEventListener('click', addQuestion)
answerButton.addEventListener('click', addAnswer)
delQuestionButton.addEventListener('click', deleteQuestion)

const delAnswerButtons = applicationForm.querySelectorAll('input[name=delAnswer]')
for (let i = 0; i < delAnswerButtons.length; i++) {
  delAnswerButtons[i].addEventListener('click', deleteAnswer)
}

function jsonBuilder() {
  const applicationForm = new Object();
  const app = document.querySelector('.ApplicationForm')
  const title = app.querySelector('input[name=title]').value
  applicationForm.title = title

  const questions = app.querySelectorAll('.question')
  const arrQuestions = [];
  if (questions.length < 1) {
    throw new Error('Анкета должна содержать хотя бы один вопрос')
  }
  for (let i = 0; i < questions.length; i++) {
    const question = new Object()
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
      url: 'http://localhost:8080/questionnaire/createApplication',
      type: 'POST',
      data: jsonBuilder(),
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      async: false,
      success: function (response) {
        alert(response.message)
        window.location.href = 'http://localhost:8080/questionnaire/forms'
      },
      error: function (response) {
        alert('error: ' + response.message)
      }
    })
    return false
  } catch (e) {
    alert(e.message)
    return false
  }
})


