
document.addEventListener('DOMContentLoaded', function(){
    //Dropdown Js 
    document.querySelectorAll('.ibox_pj-dropdown-click').forEach(function(el){
        el.addEventListener('click', function(e){e.preventDefault();dropdownMethod(this)});
    }) 
    
}, false);
function dropdownMethod(el){
    var getListEl = el.parentElement.querySelector('.ibox_pj-dropdown-menu');
    if(getListEl.classList.contains('view')){
        getListEl.classList.remove('view');
    } else {
        getListEl.classList.add('view');
    }
}
