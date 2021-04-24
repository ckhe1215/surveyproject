var selected_survey_id = 0;

var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
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

        $('#survey-list').on('click', '.clickable-row', function(event, row) {
          if($(this).hasClass('table-active')){
              $(this).removeClass('table-active');
          } else {
              $(this).addClass('table-active').siblings().removeClass('table-active');
          }
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
            author_email: $('#author_email').val(),
            surveyId: selected_survey_id,
            answerGoal: $('#answerGoal').val(),
            expiredDate: $('#expiredDate').val(),
            isExpired: false
        };

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
    },

    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            surveyId: 12345,
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
            if (choice1) cnt++;
            if (choice2) cnt++;
            if (choice3) cnt++;
            if (choice4) cnt++;
            if (choice5) cnt++;
            if (choice6) cnt++;
            if (choice7) cnt++;
            if (choice8) cnt++;
            if (choice9) cnt++;
            if (choice10) cnt++;

            var temp_survey = {
                id: $('#survey_id').val(),
                title: $('#survey_title').val(),
                author: $('#survey_author').val()
            }

            var data = {
                    surveys: temp_survey,
                    content: $('#question-content').val(),
                    choicable: $('#choice_true').is(':checked'),
                    multiple: $('#multiple').is(':checked'),
                    etcAnswer: $('#etc_answer').is(':checked'),
                    necessaryAns: $('#necessary').is(':checked'),
                    choiceCnt: cnt,
                    choice1: $('#choice1').val(),
                    choice2: $('#choice2').val(),
                    choice3: $('#choice3').val(),
                    choice4: $('#choice4').val(),
                    choice5: $('#choice5').val(),
                    choice6: $('#choice6').val(),
                    choice7: $('#choice7').val(),
                    choice8: $('#choice8').val(),
                    choice9: $('#choice9').val(),
                    choice10: $('#choice10').val()
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
                if (choice1) cnt++;
                if (choice2) cnt++;
                if (choice3) cnt++;
                if (choice4) cnt++;
                if (choice5) cnt++;
                if (choice6) cnt++;
                if (choice7) cnt++;
                if (choice8) cnt++;
                if (choice9) cnt++;
                if (choice10) cnt++;

                var temp_survey = {
                    id: $('#survey_id').val(),
                    title: $('#survey_title').val(),
                    author: $('#survey_author').val()
                }

                var data = {
                        surveys: temp_survey,
                        content: $('#question-content').val(),
                        choicable: $('#choice_true').is(':checked'),
                        multiple: $('#multiple').is(':checked'),
                        etcAnswer: $('#etc_answer').is(':checked'),
                        necessaryAns: $('#necessary').is(':checked'),
                        choiceCnt: cnt,
                        choice1: $('#choice1').val(),
                        choice2: $('#choice2').val(),
                        choice3: $('#choice3').val(),
                        choice4: $('#choice4').val(),
                        choice5: $('#choice5').val(),
                        choice6: $('#choice6').val(),
                        choice7: $('#choice7').val(),
                        choice8: $('#choice8').val(),
                        choice9: $('#choice9').val(),
                        choice10: $('#choice10').val()
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
        }
};

main.init();