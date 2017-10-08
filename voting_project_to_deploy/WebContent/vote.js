function onSuccess(googleUser) {
	var profile = googleUser.getBasicProfile();
	
	document.getElementById('gid').value=googleUser.getAuthResponse().id_token;
}

function onFailure(error) {
	alert('signIn failed');
}

function renderButton() {
	gapi.signin2.render('gSignIn', {
		'scope': 'profile email',
		'width': 150,
		'height': 30,
		'longtitle': true,
		'theme': 'dark',
		'onsuccess': onSuccess,
		'onfailure': onFailure
	});
}

function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function () {
		console.log('User signed out.');
	});
}
