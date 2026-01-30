def call(Map params) {
    emailext(
        attachLog: true,
        to: params.to,
        subject: params.subject,
        mimeType: 'text/html',
        body: """
<!DOCTYPE html>
<html>
<head>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f5f5f5;
        padding: 20px;
    }
    .container {
        max-width: 700px;
        margin: auto;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        overflow: hidden;
    }
    .header {
        background: ${params.color};
        color: white;
        padding: 25px;
        text-align: center;
    }
    .content { padding: 25px; }
    .badge {
        display: inline-block;
        padding: 6px 12px;
        border-radius: 20px;
        background: ${params.badgeColor};
        color: white;
        font-weight: bold;
        font-size: 12px;
    }
    .footer {
        background: #f8f9fa;
        padding: 15px;
        text-align: center;
        font-size: 12px;
        color: #6c757d;
    }
</style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>${params.title}</h1>
        <p>${params.subtitle}</p>
    </div>

    <div class="content">
        <p><span class="badge">${params.status}</span></p>

        <p><strong>Project:</strong> ${env.JOB_NAME}</p>
        <p><strong>Build #:</strong> ${env.BUILD_NUMBER}</p>
        <p><strong>Branch:</strong> ${env.BRANCH_NAME}</p>

        <p><strong>Image:</strong></p>
        <pre>${params.image}</pre>

        <p><strong>Triggered by:</strong> ${env.pusher ?: 'Manual'}</p>
        <p><strong>Commit:</strong> ${env.commit ?: 'N/A'}</p>
    </div>

    <div class="footer">
        Build time: ${new Date()}
    </div>
</div>
</body>
</html>
"""
    )
}
