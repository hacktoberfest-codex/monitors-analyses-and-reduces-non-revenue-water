 //Function to show password
 function myFunction() {
    var x = document.getElementById("myInput");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

const togglePassword = document.querySelector('#togglePassword');
  const password = document.querySelector('#id_password');

  togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});

// Function to check Whether both passwords
// is same or not.
function checkPassword(form) {
    password1 = form.pass.value;
    password2 = form.pass2.value;

    // If password not entered
    if (password1 == '')
        alert("Please enter Password");

    // If confirm password not entered
    else if (password2 == '')
        alert("Please enter confirm password");

    // If Not same return False.    
    else if (password1 != password2) {
        alert("\nPassword did not match: Please try again...")
        return false;
    }

    // If same return True.
    else {
        alert("Password Match: Welcome to DROP BY DROP data portal")
        return true;
    }
}



