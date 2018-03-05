var token;
var selectedGatewayId;

var ms = 15000;
$(document).ready(function(){
  token = $.session.get("token");
  console.log("token : " +token);
  console.log("token : " +token);

  //burada if login degilse login sayfasina yonlendir!
  loadHtml("fragments/menu.html",$("#menu"));
  loadHtml("dashboard.html",$("#container"));
  var data = {
    'token' : token,
  };
  getInfo(data);
});
function notificate(title,message,type){
  $.notify({
	// options
	  icon: 'glyphicon glyphicon-warning-sign',
    title: title,
	  message: message,
  },{
	 type: type,
   placement: {
		from: "bottom",
		align: "right"
	},
	animate: {
		enter: 'animated fadeInDown',
		exit: 'animated fadeOutUp'
	},
	allow_dismiss: true,
  delay:1500,
  element: 'body',
  });
}
function getInfo(data){
  request("info", function(success) {
      console.log(success);
      $("#workingGatewaySpan").text(success["workingGatewayCount"]);
      $("#unknownGatewaySpan").text(success["unknownGatewayCount"]);
      $("#brokenGatewaySpan").text(success["brokenGatewayCount"]);

      $("#workingSlaveSpan").text(success["workingSlaveCount"]);
      $("#unknownSlaveSpan").text(success["unknownSlaveCount"]);
      $("#brokenSlaveSpan").text(success["brokenSlaveCount"]);
      $("#brandDiv").text(success["company"]["name"]);
      $("#totalAlarmCountSpan").text(success["totalAlarmCount"]);
      $("#occuredAlarmCountSpan").text(success["totalOccuredAlarmCount"]);
      setTimeout(function(){
        getInfo(data);
      }, 5000);
  }, "post", data, function(error) {
      console.log(error);
      setTimeout(function(){
        getInfo(data);
      }, 5000);
  });
}
function loadHtml(file,target){
  $(target).load(file);
}
function loadContainer(file){
  $("#container").load(file);
}
function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
