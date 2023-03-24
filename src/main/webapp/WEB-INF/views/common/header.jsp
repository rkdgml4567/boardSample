<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

        <link rel="stylesheet" href="resources/css/header.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        
        <script type="text/javascript">
        $(function() { 

            //  open and close nav 
            $('#navbar-toggle').click(function() {
              $('nav ul').slideToggle();
            });


            // Hamburger toggle
            $('#navbar-toggle').on('click', function() {
              this.classList.toggle('active');
            });


            // If a link has a dropdown, add sub menu toggle.
            $('nav ul li a:not(:only-child)').click(function(e) {
              $(this).siblings('.navbar-dropdown').slideToggle("slow");

              // Close dropdown when select another dropdown
              $('.navbar-dropdown').not($(this).siblings()).hide("slow");
              e.stopPropagation();
            });


            // Click outside the dropdown will remove the dropdown class
            $('html').click(function() {
              $('.navbar-dropdown').hide();
            });
          }); 

        </script>
<title>Insert title here</title>

</head>
<body>

 <section class="navigation">
        <div class="nav-container">
            <div class="brand">
                <a href="#">Swan</a>
            </div>
            <nav>
                <div class="nav-mobile"><a id="navbar-toggle" href="#!"><span></span></span></a></div>

                <ul class="nav-list">
                        <li>
                            <a href="#!">HOME</a>
                        </li>
                        <li>
                            <a href="#!">매물</a>
                        </li>
                        <li>
                            <a href="#!">시세</a>                       
                        </li>
                         <li>
                            <a href="#!">뉴스</a>
                        </li>
                        <li>
                            <a href="#!">게시판 </a>                       
                        </li>
                </ul>
            </nav>
        </div>
    </section>
