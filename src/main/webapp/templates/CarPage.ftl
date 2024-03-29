<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../css/carpage.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script>
        function f() {
            $.ajax({
                url: "/addFavourite",
                // data: {"id": $("#linkRef").val()},
                data: {"id": $("#linkRef").attr("href"),
                       "src": $("#star").attr("src"),
                        "username": $("#userButton").attr("name")},
                dataType: "json",
                success: function(msg) {
                    $("#star").attr("src", msg.src);
                }
            })
        }
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="/home" style="all: initial; float: left; left: 20%;"><img src="../images/compass-icon.png" class="image-icon"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
<#--        <form class="form-inline my-2 my-lg-0">-->
<#--            <input class="form-control mr-sm-2" type="search" placeholder="Search model" aria-label="Search">-->
<#--            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style="color: white;">Search</button>-->
<#--        </form>-->
    </div>
    <#if user??>
        <div class="modal fade" id="carsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1>Your favourite cars: </h1>
                    </div>
                    <div class="modal-body">
                        <#list favourite_cars as car>
                            <a href="/cars/${car.id}">
                                ${car.maker.makerName} ${car.model.name}
                            </a><br>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <button data-toggle="modal" data-target="#carsModal" id="userButton" name = "${user.username}" type="button" class="btn btn-link" style="color: white;">${user.username}</button>
    <#else>
        <!-- Modal -->
        <div class="modal fade" id="elegantModalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!--Content-->
                <div class="modal-content form-elegant">
                    <!--Header-->
                    <div class="modal-header text-center">
                        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel"><strong>Sign
                                in</strong></h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!--Body-->
                    <form method="post" action="/signIn">
                        <div class="modal-body mx-4">
                            <!--Body-->
                            <div class="md-form mb-5">
                                <input type="text" name="username" id="Form-email1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-email1">Your nickname</label>
                            </div>

                            <div class="md-form pb-3">
                                <input type="password" name="password" id="Form-pass1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-pass1">Your password</label>
                            </div>

                            <div class="input-group mb-3">
                                <input type="checkbox" name="rememberMe" id="Form-remember1">
                                <label data-error="wrong" data-success="right" for="Form-remember1">&nbsp;&nbsp;Remember
                                    me</label>
                            </div>

                            <div class="text-center mb-3">
                                <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a">Sign
                                    in
                                </button>
                            </div>
                            <input name="originPage" value="/cars/${car.id}" style="display: none">

                        </div>
                </div>
                </form>
                <!--/.Content-->
            </div>
        </div>
        <!-- Modal -->


    <#--                SIGN UP FORM-->
        <!-- Modal -->
        <div class="modal fade" id="SignUpForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!--Content-->
                <div class="modal-content form-elegant">
                    <!--Header-->
                    <div class="modal-header text-center">
                        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel"><strong>Sign
                                up</strong></h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!--Body-->
                    <form method="post" action="/signUp">
                        <div class="modal-body mx-4">
                            <!--Body-->
                            <div class="md-form mb-5">
                                <input type="text" name="username" id="Form-email1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-email1">Your nickname</label>
                            </div>

                            <div class="md-form pb-3">
                                <input type="password" name="password" id="Form-pass1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-pass1">Your password</label>
                            </div>
                            <input name="originPage" value="/cars/${car.id}" style="display: none">


                            <div class="text-center mb-3">
                                <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a" style="color: white">Sign
                                    up
                                </button>
                            </div>
                        </div>
                </div>
                </form>
                <!--/.Content-->
            </div>
        </div>
        <!-- Modal -->

        <div class="text-center">
            <a href="" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm" style="color: white">Sign
                In</a>
        </div>

        <div class="text-center">
            <a href="" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#SignUpForm" style="color: white">Sign Up</a>
        </div>
    </#if>
</nav>
<div id="container">
    <div id="content">
        <a id="linkRef" href="/cars/${car.id}">
            <#--                <img style="width: 80%" height="300px" src="../images/cars/Renault_Duster.png">-->

            <img style="width: 100%" height="100%" src="../images/cars/${car.imagePath}">
        </a>
    </div>
    <div id="menu">
        <div id="top" style="float: top; height: 10%">
            <br><br><#if user??>
            <a href="#" id="image" onclick="f()">
                <img id="star" src="${src}" style="position: relative; width: 65px; height: 65px; float: left; bottom: 5px; left: 30px">
            </a>
            </#if>
            <h1>${car.maker.makerName} ${car.model.name}</h1></div>
        <div id="model-info" style="float: bottom; height: 70%">
            <ul>
                <br><br>
                <li>Maker: ${car.maker.makerName}</li><br>
                <li>Year: ${car.year?int?c}</li><br>
                <li>Transmission: ${car.transmission}</li><br>
                <li>Engine type: ${car.engine}</li><br>
                <li>Car type: ${car.type}</li><br>
                <li>Capacity: ${car.capacity}</li><br>
                <li>Average price: ${car.avgPrice} rub</li>
            </ul>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>