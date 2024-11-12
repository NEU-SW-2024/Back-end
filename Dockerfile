# 使用官方MySQL镜像作为基础镜像
FROM mysql:latest

# 设置环境变量
ENV MYSQL_ROOT_PASSWORD=password
#设置编码为UTF-8
ENV LANG C.UTF-8
RUN echo "[mysqld]" >> /etc/mysql/my.cnf \
    && echo "character-set-server=utf8mb4" >> /etc/mysql/my.cnf \
    && echo "collation-server=utf8mb4_general_ci" >> /etc/mysql/my.cnf \
    && echo "[client]" >> /etc/mysql/my.cnf \
    && echo "default-character-set=utf8mb4" >> /etc/mysql/my.cnf \
    && echo "[mysql]" >> /etc/mysql/my.cnf \
    && echo "default-character-set=utf8mb4" >> /etc/mysql/my.cnf
# 复制初始化SQL文件到镜像中
COPY sql/init.sql /docker-entrypoint-initdb.d/

# 复制要加载的SQL文件到镜像中（假设文件名为data.sql）
COPY sql/ry_20240629.sql /docker-entrypoint-initdb.d/

# 暴露MySQL默认端口
EXPOSE 3306