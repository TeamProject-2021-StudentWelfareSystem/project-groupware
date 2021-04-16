function selectAll(selectAll)  {
  const checkboxes 
       = document.getElementsByName('check');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked;
  })
}