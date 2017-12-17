<template>
	<div id="user">
		<div class="header">
			<h2 class="page-title">Users</h2>
			<button v-on:click="showModal = true" type="button" class="btn btn-primary button">Create User</button>
		</div>
		​<table class="table table-striped border-top">
      <thead>
        <tr>
          <th style="border-top:none" >Username</th>
          <th style="border-top:none" class="table-opt">Options</th>
        </tr>
      </thead>
      <tbody>
        <tr v-bind:key="user.id" v-for="user in users">
          <td style="vertical-align:middle">{{ user.userName }}</td>
          <td class="table-opt"><button v-on:click="deleteUser(user.id)" type="button" class="btn btn-danger">Delete</button></td>
        </tr>
      </tbody>
    </table>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <div class="header">
          <h4 class="page-title">Create User</h4>
          <span v-on:click="showModal = false" class="close button">&times;</span>
        </div>
        <div class="content">
          <div class="form-group">
            <label>User Name</label>
            <input v-model="inputUserName" type="text" class="form-control">
          </div>
          <button v-on:click="createUser" class="btn btn-default">Create</button>
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
      users: [],
      inputUserName: null,
      showModal: false
    }
	},
	methods: {
    getUsers: function(){
      this.$axios.get('http://localhost:8080/project/users')
      .then(response => { this.users = response.data; });
    },
    createUser: function(){
      var lastuserid = (this.users.length != 0) ? this.users[this.users.length-1].id :  0;
      this.$axios.put('http://localhost:8080/project/users/'+(lastuserid+1),
        '{"userName":"'+this.inputUserName+'"}', {headers: {"Content-Type": "application/json" }})
        .then(response => { this.createScoreForUser(response.data.id); location.reload();
        });
    },
    createScoreForUser: function(userId){
      this.$axios.post('http://localhost:8080/project/users/'+userId+'/score',
        '{"score":"0"}', {headers: {"Content-Type": "application/json" }})
    },
    deleteUser: function(id){
      this.$axios.delete('http://localhost:8080/project/users/'+id);
      location.reload();
    }
	},
	created(){
    this.getUsers();
	}
}
</script>
