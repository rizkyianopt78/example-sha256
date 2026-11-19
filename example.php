<?php

$clientId = "YOUR_CLIENT_ID";
$secret = "YOUR_CLIENT_SECRET";

// Generate 8 random bytes and convert to hexadecimal
$externalId = bin2hex(random_bytes(8));

// Generate RFC3339 timestamp
$timestamp = date(DATE_RFC3339);

// HMAC-SHA256 (Merchant / Customer inquiry POST)
$hmacSigningString = sprintf(
    "%s|%s|%s",
    $clientId,
    $externalId,
    $timestamp
);

// Generate HMAC-SHA256 signature
$hmacSignature = hash_hmac(
    'sha256',
    $hmacSigningString,
    $secret
);

echo $hmacSignature;

// Optional: display generated values
echo "\nExternal ID: " . $externalId;
echo "\nTimestamp: " . $timestamp;

?>