<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<form name='form' align='center' method='post' action="${path}/survey/insertProc">
     <table border='1' align='center' width='60%' height='400px'>
         <caption style="font-weight: bolder;">설문작성</caption>
        
         <tr height="13%;"style="font-weight: bolder;">
             <td colspan='2'>(*)제목<br>
             	<input type='text' name='survey_name' size="60" />
             </td>
         </tr>
         <tr>
             <td colspan='2' style="font-weight: bolder;">설문설명<br>
             	<textarea type='text' name='survey_content' rows="6" cols='60'  style="resize:none;"></textarea>
             </td>
         </tr>
         <tr>
             <td colspan='2'>
                 <div id="questions-container">
                     <!-- Questions will be appended here -->
                 </div>
                 <button type="button" onclick="addQuestion()">질문 추가</button>
             </td>
         </tr>
         <tr>
         	<td colspan='2' align='right'>
         		설문종료일자<input type='date' name="survey_end"><br>
         		포인트 <input type='text' name='survey_point' size="10" placeholder="POINT"/><br>
         		<button type='submit'>작성완료</button>
         	</td>
         </tr>
	</table>
</form>
   
     <script>
       	let questionCount=0;

        function addQuestion() {
        	questionCount++;
            const questionsContainer = document.getElementById('questions-container');

            // Create question div
            const questionDiv = document.createElement('div');
            questionDiv.id = `question-${"${questionCount}"}`;
			            
            // Create question title
            const questionTitle = document.createElement('h3');
            questionTitle.innerText = `질문.${"${questionCount}"}`;
            questionDiv.appendChild(questionTitle);

            // Create question input
            const questionInput = document.createElement('input');
            questionInput.type = 'text';
            questionInput.name = `question_${"${questionCount}"}`;
            questionInput.placeholder = '질문을 입력해주세요';
            questionDiv.appendChild(questionInput);

            // Create options container
            const optionsContainer = document.createElement('div');
            optionsContainer.id = `options-${"${questionCount}"}`;
            questionDiv.appendChild(optionsContainer);

            // Create add option button
            const addOptionButton = document.createElement('button');
            addOptionButton.type = 'button';
            addOptionButton.innerText = '문항 추가';
            addOptionButton.onclick = function() {
                addOption(optionsContainer);
            };
            questionDiv.appendChild(addOptionButton);

            // Create delete question button
            const deleteQuestionButton = document.createElement('button');
            deleteQuestionButton.type = 'button';
            deleteQuestionButton.innerText = '질문 삭제';
            deleteQuestionButton.onclick = function() {
            	questionCount--;
            	questionDiv.remove();
            };
            questionDiv.appendChild(deleteQuestionButton);

            // Append question div to the main container
            questionsContainer.appendChild(questionDiv);
        }

        function addOption(optionsContainer) {
            const optionDiv = document.createElement('div');
            optionDiv.className = 'option';

            const optionInput = document.createElement('input');
            optionInput.type = 'text';
            optionInput.name = `option_${"${questionCount}"}`;
            optionInput.placeholder = `문항_${"${questionCount}"}`;
            optionDiv.appendChild(optionInput);

            const deleteOptionButton = document.createElement('button');
            deleteOptionButton.type = 'button';
            deleteOptionButton.innerText = '문항삭제';
            deleteOptionButton.onclick = function() {
            	optionDiv.remove();
            };
            optionDiv.appendChild(deleteOptionButton);

            optionsContainer.appendChild(optionDiv);
        }
    </script>