<template>
	<div id="user">
		<div class="header">
			<h2 class="page-title">Competition</h2>
		</div>
		<div class="content" style="padding-top:100px">
			<div class="username">
				<div class="form-group">
					<label for="sel1">Username:</label>
					<select class="form-control" id="sel1" v-model="inputUser">
						<option v-for="user in users" v-bind:key="user.id" v-bind:value="user.id">
    					{{ user.userName }}
  					</option>
					</select>
				</div>
			</div>
			<div class="question" v-bind:key="record.id" v-for="(record, index) in questions">
				<div class="form-group">
            <label v-if="index == 0 || (record.question.id != questions[index-1].question.id)"><b> Question:</b> {{record.question.question}}</label> <br>
						<label class="radio-inline"><input type="radio" v-bind:name="record.question.id" :value="record.value" v-model="answers[record.question.id]"> {{record.value}}</label>
        </div>
			</div>
			<button v-on:click="submitCompetition" class="btn btn-default">Submit</button>
		</div>
	</div>
</template>

<script>
export default {
  name: 'competition',
  data () {
    return {
			questions: [],
			choices: [],
			users: [],
			answers: [],
      inputUser: null
    }
	},
	methods: {
		getCompetition: function(){
			this.$axios.get('http://localhost:8080/project/competition/?page=0&size=40')
			.then(response => { this.questions = response.data["Page 1 of 1 containing com.example.demo.model.Question instances"].content;
				console.log(this.questions);
			});
		},
		getUsers: function(){
      this.$axios.get('http://localhost:8080/project/users')
      .then(response => { this.users = response.data; });
		},
		submitCompetition: function(){
			var score = 0;
			for(var i = 0; i < this.questions.length; i++){
				if(i == 0 || (this.questions[i].question.id != this.questions[i-1].question.id)){
					var questionId = this.questions[i].question.id;
					if(this.answers[questionId] == this.questions[i].value)
						score += 5;
				}
			}
			this.$axios.post('http://localhost:8080/project/users/'+this.inputUser+'/score',
        '{"score":"'+score+'"}', {headers: {"Content-Type": "application/json" }})
		}
	},
	created(){
		this.getUsers();
		this.getCompetition();
	}
}
</script>