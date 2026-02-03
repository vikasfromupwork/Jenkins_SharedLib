def call() {
    sh '''
        # Clean Trivy cache to fix corrupted database
        echo "[INFO] Cleaning Trivy cache..."
        
        # Method 1: Clean using trivy clean command
        trivy clean --all || true
        
        # Method 2: Also manually clean directories
        rm -rf ~/.cache/trivy || true
        rm -rf ~/.trivy || true
        
        # Create fresh cache directory
        mkdir -p /tmp/trivy-cache
        
        # Download fresh database
        echo "[INFO] Downloading fresh Trivy database..."
        trivy --cache-dir /tmp/trivy-cache image --download-db-only --no-progress
        
        # Run filesystem scan with exit code 0 to continue on vulnerabilities
        echo "[INFO] Running Trivy filesystem scan..."
        trivy --cache-dir /tmp/trivy-cache fs . --exit-code 0 --severity HIGH,CRITICAL --no-progress
        
        echo "[INFO] Trivy scan completed"
    '''
}
