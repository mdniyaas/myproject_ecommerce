$ErrorActionPreference = "Stop"

$root = Split-Path -Parent (Split-Path -Parent $MyInvocation.MyCommand.Path)
$staticSource = Join-Path $root "demo\src\main\resources\static"
$publicDir = Join-Path $root "public"

if (-not (Test-Path $publicDir)) {
    New-Item -ItemType Directory -Path $publicDir -Force | Out-Null
}

$cssDest = Join-Path $publicDir "css"
$jsDest = Join-Path $publicDir "js"

if (Test-Path $cssDest) { Remove-Item $cssDest -Recurse -Force }
if (Test-Path $jsDest) { Remove-Item $jsDest -Recurse -Force }

Copy-Item -Path (Join-Path $staticSource "css") -Destination $cssDest -Recurse -Force
Copy-Item -Path (Join-Path $staticSource "js") -Destination $jsDest -Recurse -Force

Write-Host "Copied static assets to public/css and public/js"
