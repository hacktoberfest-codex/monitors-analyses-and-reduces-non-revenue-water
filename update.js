var subjectObject = {
    "2": {
      "250": [],
      "500": [],
      "1000": [],
    },
    "3": {
      "250": [],
      "500": [],
      "1000": [],
      "1500": [],
    },
    "4": {
      "250": [],
      "500": [],
      "1000": [],
      "1500": [],
      "2000": [],
  
  
    },
    "5": {
      "250": [],
      "500": [],
      "1000": [],
      "1500": [],
      "2000": [],
      "2500": [],
    },
    "6-8": {
      
      "1500": [],
      "2000": [],
      "2500": [],
      "3000": [],
      "3500": [],
      "4000": [],
    },
    "9-12": {
      "2500": [],
      "3000": [],
      "3500": [],
      "4000": [],
      "4500": [],
      "5000": [],
      "5500": [],
      "6000": [],
  
    },
    "12+": {
      "5000": [],
      "5500": [],
      "6000": [],
      "6000+": [],
    },
  }
  window.onload = function() {
    var subjectSel = document.getElementById("subject");
    var topicSel = document.getElementById("topic");
    var chapterSel = document.getElementById("chapter");
    for (var x in subjectObject) {
      subjectSel.options[subjectSel.options.length] = new Option(x, x);
    }
    subjectSel.onchange = function() {
      //empty Chapters- and Topics- dropdowns
      
      topicSel.length = 1;
      //display correct values
      for (var y in subjectObject[this.value]) {
        topicSel.options[topicSel.options.length] = new Option(y, y);
      }
    }
    
    
  }