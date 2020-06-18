set /p Tags=Enter the tags you want to run:

if "%Tags%" == "" (
 mvn test
) ELSE (
 mvn test -Dgroups=%Tags%
)

pause
