const createBookForm = document.getElementById('book-create-form');

createBookForm.addEventListener('submit', (e) => {
   e.preventDefault();

   fetch("/api/books", {
       body: JSON.stringify({
           name: e.target.bookName.value,
           author_id: e.target.authorId.value,
       }),
       method: 'POST',
       headers: {
           "content-type": "application/json",
       }
   })
       .then((res) => {
           console.log(res);
           return res.json();
       })
       .then((data) => {
           console.log(data);
       })
       .catch((err) => console.error(err));
});