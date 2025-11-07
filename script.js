// ----------------- STUDENT -----------------
const studentForm = document.getElementById("feedbackForm");
if(studentForm){
    studentForm.addEventListener("submit", function(e){
        e.preventDefault();
        const course = document.getElementById("course").value;
        const faculty = document.getElementById("faculty").value;
        const rating = parseInt(document.getElementById("rating").value);
        const comment = document.getElementById("comment").value;

        const feedbacks = JSON.parse(localStorage.getItem("feedbacks") || "[]");
        feedbacks.push({course, faculty, rating, comment});
        localStorage.setItem("feedbacks", JSON.stringify(feedbacks));

        const msg = document.getElementById("message");
        msg.textContent = "✅ Feedback saved successfully!";
        msg.style.opacity = 1;

        studentForm.reset();

        setTimeout(() => { msg.style.opacity = 0; }, 2500);
    });
}

// ----------------- FACULTY -----------------
const facultyForm = document.getElementById("facultyForm");
if(facultyForm){
    facultyForm.addEventListener("submit", function(e){
        e.preventDefault();
        const facultyName = document.getElementById("facultyName").value;
        const feedbacks = JSON.parse(localStorage.getItem("feedbacks") || "[]");

        const facultyFeedbacks = feedbacks.filter(fb => fb.faculty.toLowerCase() === facultyName.toLowerCase());

        let reportDiv = document.getElementById("report");
        reportDiv.innerHTML = "";

        if(facultyFeedbacks.length === 0){
            reportDiv.innerHTML = "<p>No feedback found for this faculty.</p>";
            return;
        }

        let total = 0;
        facultyFeedbacks.forEach(fb => {
            total += fb.rating;
            reportDiv.innerHTML += `
                <div>
                    <strong>Course:</strong> ${fb.course} <br>
                    <strong>Rating:</strong> ${fb.rating} <br>
                    <strong>Comment:</strong> ${fb.comment}
                </div>
                <hr>`;
        });
        const avg = (total / facultyFeedbacks.length).toFixed(2);
        reportDiv.innerHTML += `<p><strong>Total Feedbacks:</strong> ${facultyFeedbacks.length}</p>
                                <p><strong>Average Rating:</strong> ${avg}</p>`;
    });
}

// ----------------- ADMIN -----------------
const adminBtn = document.getElementById("viewSummary");
if(adminBtn){
    adminBtn.addEventListener("click", function(){
        const feedbacks = JSON.parse(localStorage.getItem("feedbacks") || "[]");
        const adminDiv = document.getElementById("adminReport");
        adminDiv.innerHTML = "";

        if(feedbacks.length === 0){
            adminDiv.innerHTML = "<p>No feedback submitted yet.</p>";
            return;
        }

        const facultyMap = {};
        feedbacks.forEach(fb => {
            if(!facultyMap[fb.faculty]) facultyMap[fb.faculty] = [];
            facultyMap[fb.faculty].push(fb.rating);
        });

        for(const faculty in facultyMap){
            const ratings = facultyMap[faculty];
            const avg = (ratings.reduce((a,b)=>a+b,0)/ratings.length).toFixed(2);
            adminDiv.innerHTML += `<p>📌 <strong>Faculty:</strong> ${faculty} | <strong>Average Rating:</strong> ${avg}</p>`;
        }
    });
}
