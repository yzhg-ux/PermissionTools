package cn.yzhg.permission;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PermissionTools.PermissionListener {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but_apply_permission = findViewById(R.id.but_apply_permission);
        Button but_signle_permission = findViewById(R.id.but_signle_permission);
        but_apply_permission.setOnClickListener(v -> applyAllPermission());
        but_signle_permission.setOnClickListener(v -> applySignlePermission());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void applySignlePermission() {
        new PermissionTools
                .Builder()
                .setPermissionListener(() -> Toast.makeText(MainActivity.this, "权限申请成功", Toast.LENGTH_SHORT).show())
                .applyPermission(this, PermissionTools.ACCESS_FINE_LOCATION, PermissionTools.ACCESS_FINE_LOCATION_CODE);
    }

    private void applyAllPermission() {
        new PermissionTools.Builder()
                .setPermissionListener(() -> Toast.makeText(MainActivity.this, "权限申请成功", Toast.LENGTH_SHORT).show())
                .morePermission(this, PermissionTools.permission);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionTools.setPermissionListener(this);
        PermissionTools.onRequestAllPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 获取权限失败
     *
     * @param fileIndex
     */
    @Override
    public void permissionFile(int fileIndex) {

    }
}
