var selected_survey_id;
var qCnt = $('#question-size').val();
for(var i = 0; i < qCnt; i++) {
    $("form-check-input, .ans1").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans2").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans3").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans4").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans5").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans6").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans7").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans8").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans9").eq(i).attr("name", "select-one-"+(i+1));
    $("form-check-input, .ans10").eq(i).attr("name", "select-one-"+(i+1));
}

var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            if (confirm("10포인트가 차감됩니다. 업로드하시겠습니까?"))
                _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        })

        $('#btn-create-survey').on('click', function () {
            _this.createSurvey();
        })

        $('#choice_true').on('click', function () {
            $('#choice_option').css("display", "block");
        })

        $('#choice_false').on('click', function () {
            $('#choice_option').css("display", "none");
        })

        $('#btn-add-question').on('click', function () {
            _this.createQuestion();
        })

        $('#btn-no-more-question').on('click', function () {
            _this.createLastQuestion();
        })

        $('#btn-answer-save').on('click', function () {
            _this.createParticipation();
            _this.createAnswer();
        })

        $('.btn-question-delete').on('click', function () {
            var id = $(this).attr("id");
            _this.deleteQuestion(id);
        })

        $('.btn-survey-delete').on('click', function () {
            var id = $(this).attr("id");
            _this.deleteSurvey(id);
        })

        $('#btn-additional-question').on('click', function () {
            _this.createAdditionalQuestion();
        })

        $('#survey-list').on('click', '.clickable-row', function(event, row) {
            if($(this).hasClass('table-active')){
                $(this).removeClass('table-active');
            } else {
                $(this).addClass('table-active').siblings().removeClass('table-active');
            }
        });

        $('#survey-list tr').on('click', function(event, row) {
            var tdArr = new Array();
            var tr = $(this);
            var td = tr.children();

            td.each(function(i){
                tdArr.push(td.eq(i).text());
            });

            var id = td.eq(0).text();
            var title = td.eq(1).text();
            selected_survey_id = id;
        });

        var detail_author_email = $('#detail_author_email').val();
        var detail_user_email = $('#detail_user_email').val();
        if (detail_author_email == detail_user_email) {
            $('#post-control').css("display", "block");
        }
        else {
            $('#add-reply').css("display", "block");
        }
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            author: $('#author').val(),
            authorEmail: $('#author_email').val(),
            surveyId: selected_survey_id,
            answerGoal: $('#answerGoal').val(),
            expiredDate: $('#expiredDate').val(),
            isExpired: false
        };

        if (parseInt($('#user-point').val()) >= 10)
        {
            $.ajax({
                type: 'POST',
                url: '/api/v1/posts',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('글이 등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
        else
            alert('포인트가 부족합니다.');
    },

    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            surveyId: selected_survey_id,
            answerGoal: $('#answerGoal').val(),
            expiredDate: $('#expiredDate').val(),
            isExpired: false
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts/detail/' + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').text();

        $.ajax({
             type: 'DELETE',
             url: '/api/v1/posts/' + id,
             dataType: 'json',
             contentType: 'application/json; charset=utf-8',
         }).done(function() {
             alert('글이 삭제되었습니다.');
             window.location.href = '/';
         }).fail(function (error) {
             alert(JSON.stringify(error));
         });
    },

    createSurvey : function () {
        var data = {
                title: $('#survey_title').val(),
                author: $('#user_email').val(),
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/surveys',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                window.location.href = '/questions/save';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },

     createQuestion : function () {
        var choice1 = $('#choice1').val();
        var choice2 = $('#choice2').val();
        var choice3 = $('#choice3').val();
        var choice4 = $('#choice4').val();
        var choice5 = $('#choice5').val();
        var choice6 = $('#choice6').val();
        var choice7 = $('#choice7').val();
        var choice8 = $('#choice8').val();
        var choice9 = $('#choice9').val();
        var choice10 = $('#choice10').val();
        var cnt = 0;
        if (choice1)
            cnt++;
        else
            choice1 = null;
        if (choice2)
            cnt++;
        else
            choice2 = null;
        if (choice3)
            cnt++;
        else
            choice3 = null;
        if (choice4)
            cnt++;
        else
            choice4 = null;
        if (choice5)
            cnt++;
        else
            choice5 = null;
        if (choice6)
            cnt++;
        else
            choice6 = null;
        if (choice7)
            cnt++;
        else
            choice7 = null;
        if (choice8)
            cnt++;
        else
            choice8 = null;
        if (choice9)
            cnt++;
        else
            choice9 = null;
        if (choice10)
            cnt++;
        else
            choice10 = null;

        var data = {
                surveyId: $('#survey_id').val(),
                content: $('#question-content').val(),
                choicable: $('#choice_true').is(':checked'),
                multiple: $('#multiple').is(':checked'),
                etcAnswer: $('#etc_answer').is(':checked'),
                necessaryAns: $('#necessary').is(':checked'),
                choiceCnt: cnt,
                choice1: choice1,
                choice2: choice2,
                choice3: choice3,
                choice4: choice4,
                choice5: choice5,
                choice6: choice6,
                choice7: choice7,
                choice8: choice8,
                choice9: choice9,
                choice10: choice10
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/questions',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                window.location.href = '/questions/save';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },

    createLastQuestion : function () {
                var choice1 = $('#choice1').val();
                var choice2 = $('#choice2').val();
                var choice3 = $('#choice3').val();
                var choice4 = $('#choice4').val();
                var choice5 = $('#choice5').val();
                var choice6 = $('#choice6').val();
                var choice7 = $('#choice7').val();
                var choice8 = $('#choice8').val();
                var choice9 = $('#choice9').val();
                var choice10 = $('#choice10').val();
                var cnt = 0;
                if (choice1)
                    cnt++;
                else
                    choice1 = null;
                if (choice2)
                    cnt++;
                else
                    choice2 = null;
                if (choice3)
                    cnt++;
                else
                    choice3 = null;
                if (choice4)
                    cnt++;
                else
                    choice4 = null;
                if (choice5)
                    cnt++;
                else
                    choice5 = null;
                if (choice6)
                    cnt++;
                else
                    choice6 = null;
                if (choice7)
                    cnt++;
                else
                    choice7 = null;
                if (choice8)
                    cnt++;
                else
                    choice8 = null;
                if (choice9)
                    cnt++;
                else
                    choice9 = null;
                if (choice10)
                    cnt++;
                else
                    choice10 = null;

                var data = {
                        surveyId: $('#survey_id').val(),
                        content: $('#question-content').val(),
                        choicable: $('#choice_true').is(':checked'),
                        multiple: $('#multiple').is(':checked'),
                        etcAnswer: $('#etc_answer').is(':checked'),
                        necessaryAns: $('#necessary').is(':checked'),
                        choiceCnt: cnt,
                        choice1: choice1,
                        choice2: choice2,
                        choice3: choice3,
                        choice4: choice4,
                        choice5: choice5,
                        choice6: choice6,
                        choice7: choice7,
                        choice8: choice8,
                        choice9: choice9,
                        choice10: choice10
                    };

                $.ajax({
                    type: 'POST',
                    url: '/api/v1/questions',
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function() {
                    window.location.href = '/posts/save';
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
        },

        createAdditionalQuestion : function () {
            var surveyId = $('#survey_id').val();
            var choice1 = $('#choice1').val();
            var choice2 = $('#choice2').val();
            var choice3 = $('#choice3').val();
            var choice4 = $('#choice4').val();
            var choice5 = $('#choice5').val();
            var choice6 = $('#choice6').val();
            var choice7 = $('#choice7').val();
            var choice8 = $('#choice8').val();
            var choice9 = $('#choice9').val();
            var choice10 = $('#choice10').val();
            var cnt = 0;
            if (choice1)
                cnt++;
            else
                choice1 = null;
            if (choice2)
                cnt++;
            else
                choice2 = null;
            if (choice3)
                cnt++;
            else
                choice3 = null;
            if (choice4)
                cnt++;
            else
                choice4 = null;
            if (choice5)
                cnt++;
            else
                choice5 = null;
            if (choice6)
                cnt++;
            else
                choice6 = null;
            if (choice7)
                cnt++;
            else
                choice7 = null;
            if (choice8)
                cnt++;
            else
                choice8 = null;
            if (choice9)
                cnt++;
            else
                choice9 = null;
            if (choice10)
                cnt++;
            else
                choice10 = null;

            var data = {
                    surveyId: $('#survey_id').val(),
                    content: $('#question-content').val(),
                    choicable: $('#choice_true').is(':checked'),
                    multiple: $('#multiple').is(':checked'),
                    etcAnswer: $('#etc_answer').is(':checked'),
                    necessaryAns: $('#necessary').is(':checked'),
                    choiceCnt: cnt,
                    choice1: choice1,
                    choice2: choice2,
                    choice3: choice3,
                    choice4: choice4,
                    choice5: choice5,
                    choice6: choice6,
                    choice7: choice7,
                    choice8: choice8,
                    choice9: choice9,
                    choice10: choice10
                };

            $.ajax({
                type: 'POST',
                url: '/api/v1/questions',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                window.location.href = '/questions/update/' + surveyId;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },

        createParticipation : function () {
            var data = {
                userEmail: $('#userEmail').val(),
                surveyId: $('#surveyId').val()
            }
            $.ajax({
                type: 'POST',
                url: '/api/v1/participations',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            });
        },

        createAnswer : function () {
            var questionCnt = $('#question-size').val();
            for(var i = 0; i < questionCnt; i++) {
                $('.radio-select .ans1').attr("name", "select-one"+"-i");
                var essayAnswer = $('.essay-content').eq(i).val();
                if (!essayAnswer)
                    essayAnswer = null;
                var data = {
                    questionId : $('.question-id').eq(i).val(),
                    answer1 : $('.ans1').eq(i).is(':checked'),
                    answer2 : $('.ans2').eq(i).is(':checked'),
                    answer3 : $('.ans3').eq(i).is(':checked'),
                    answer4 : $('.ans4').eq(i).is(':checked'),
                    answer5 : $('.ans5').eq(i).is(':checked'),
                    answer6 : $('.ans6').eq(i).is(':checked'),
                    answer7 : $('.ans7').eq(i).is(':checked'),
                    answer8 : $('.ans8').eq(i).is(':checked'),
                    answer9 : $('.ans9').eq(i).is(':checked'),
                    answer10 : $('.ans10').eq(i).is(':checked'),
                    essayAnswer : essayAnswer
                };

                $.ajax({
                    type: 'POST',
                    url: '/api/v1/answers',
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function() {
                    window.location.href = '/';
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
        },

        deleteQuestion : function(id) {
            $.ajax({
                 type: 'DELETE',
                 url: '/api/v1/questions/' + id,
                 dataType: 'json',
                 contentType: 'application/json; charset=utf-8',
            }).done(function() {
                alert('질문이 삭제되었습니다.');
                location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },

        deleteSurvey : function(id) {
            $.ajax({
                 type: 'DELETE',
                 url: '/api/v1/surveys/' + id,
                 dataType: 'json',
                 contentType: 'application/json; charset=utf-8',
            }).done(function() {
                alert('설문이 삭제되었습니다.');
                location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
};

main.init();