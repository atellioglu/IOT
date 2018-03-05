const mainUrl = "http://localhost:8080/";
function request(url,successCallBack,method,data,errorCallBack){
  if(method == undefined){
    method = "POST";
  }
  url = mainUrl + url;
  $.ajax({
     'url': url,
     'method' : method,
     'cache': false,
     'data' : JSON.stringify(data),
     'contentType' : "application/json; charset=utf-8",
     'dataType' : "json",
     'success': function(data){
       successCallBack(data);
     },
     'error': function(data){
       if(errorCallBack!=undefined){
         errorCallBack(data);
       }

     }
   });
}
function hasStorage(){
  if (typeof(Storage) !== "undefined") {
    console.log("Has storage!");
    return true;
  } else {
    console.log("No web storage!");
    return false;
  }
}
function save(key,value,session){
  if(!hasStorage())
    return;
  if(session != undefined){
    console.log("saved into session: "+key+" - "+value);
    sessionStorage.key = value;
    return true;
  }else{
    console.log("saved into localStorage: "+key+" - "+value);
    localStorage.key = value;
    return true;
  }
}
function get(key,storage){
  return storage.key;
}
function getToken(){
  if(!hasStorage()){
    return undefined;
  }
  console.log("get token from" + localStorage.token);
  return localStorage.getItem('token');
}
function saveToken(value){
  if(!hasStorage())
    return;
    if(localStorage != undefined){
      localStorage.setItem("token",value);
      console.log("Saved into localStorage token = "+value);
    }
}
function hasLogin(){
  if(localStorage.token == undefined){
      console.log(localStorage.token);
      return false;
  }
  return true;
}
