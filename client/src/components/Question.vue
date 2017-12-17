<template>
	<div id="user">
		<div class="header">
			<h2 class="page-title">Questions</h2>
			<button v-on:click="showCreateModal = true" type="button" class="btn btn-primary button">Create Questions</button>
		</div>
		​<table class="table table-striped border-top">
      <thead>
        <tr>
          <th style="border-top:none" >Question</th>
          <th style="border-top:none" class="table-opt">Options</th>
        </tr>
      </thead>
      <tbody>
        <tr v-bind:key="record.id" v-for="record in questions">
          <td style="vertical-align:middle">{{ record.question }}</td>
          <td class="table-opt"><button v-on:click="deleteQuestion(record.id)" type="button" class="btn btn-danger">Delete</button></td>
          <td class="table-opt"><button v-on:click="showQuestionDetail(record)" type="button" class="btn btn-info">Details</button></td>
        </tr>
      </tbody>
    </table>
    <div v-if="showCreateModal" class="modal">
      <div class="modal-content">
        <div class="header">
          <h4 class="page-title">Create Question</h4>
          <span v-on:click="showCreateModal = false" class="close button">&times;</span>
        </div>
        <div class="content">
          <div class="form-group">
            <label><b>Question</b></label>
            <input v-model="inputQuestion" type="text" class="form-control">
            <label><b>Choice A</b></label>
            <input v-model="inputChoices[0]" type="text" class="form-control">
            <label><b>Choice B</b></label>
            <input v-model="inputChoices[1]" type="text" class="form-control">
            <label><b>Choice C</b></label>
            <input v-model="inputChoices[2]" type="text" class="form-control">
            <label><b>Choice D</b></label>
            <input v-model="inputChoices[3]" type="text" class="form-control">
            <label><b>Answer</b></label><br>
            <input type="radio" name="gender" value="a" v-model="inputAnswer"> Choice A <br>
            <input type="radio" name="gender" value="b" v-model="inputAnswer"> Choice B <br>
            <input type="radio" name="gender" value="c" v-model="inputAnswer"> Choice C <br>
            <input type="radio" name="gender" value="d" v-model="inputAnswer"> Choice D
          </div>
          <button v-on:click="createQuestion" class="btn btn-default">Create</button>
        </div>
      </div>
    </div>
    <div v-if="showDetailModal" class="modal">
      <div class="modal-content">
        <div class="header">
          <h4 class="page-title">Question Detail</h4>
          <span v-on:click="showDetailModal = false" class="close button">&times;</span>
        </div>
        <div class="content">
          <b>Question:</b> {{ activeQuestion.question }} <br>
          <b>Choices:</b>
          <ul>
            <li v-bind:key="record.id" v-for="record in activeQuestion.choices" >{{record.value}}</li>
          </ul>
        </div>
      </div>
    </div>
	</div>
</template>

<script>
export default {
  name: 'user',
  data () {
    return {
      questions: null,
      inputQuestion: null,
      inputChoices: [],
      inputAnswer: null,
      activeQuestion: null,
      showCreateModal: false,
      showDetailModal: false,
    }
	},
	methods: {
    getQuestions: function(){
      this.$axios.get('http://localhost:8080/project/questions')
      .then(response => { this.questions = response.data; });
    },
    showQuestionDetail: function(question){
      this.activeQuestion = question;
      this.$axios.get('http://localhost:8080/project/questions/'+question.id+'/choices')
      .then(response => {
        this.activeQuestion.choices = response.data;
        this.showDetailModal = true;
      });
    },
    createQuestion: function(){
      var questionId = null;
      this.$axios.post('http://localhost:8080/project/questions',
        '{"question":"'+this.inputQuestion+'"}', {headers: {"Content-Type": "application/json" }})
        .then(response => { this.createChoicesForQestion(response.data.id); location.reload();
        });
    },
    createChoicesForQestion: function(questionId){
      var choices = ['a','b','c','d'];
      for(var i = 0; i < this.inputChoices.length; i++){
        this.$axios.post('http://localhost:8080/project/questions/'+questionId+'/choices',
        '{"choices":"'+choices[i]+'", "value":"'+this.inputChoices[i]+'", "isTrue":"'+((choices[i] == this.inputAnswer) ? true : false)+'"}',
        {headers: {"Content-Type": "application/json" }});
      }
    },
    deleteQuestion: function(id){
      this.$axios.delete('http://localhost:8080/project/questions/'+id);
      location.reload();
    }
	},
	created(){
    this.getQuestions();
	}
}
</script>
