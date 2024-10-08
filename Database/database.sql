PGDMP      .                |            WarrantyControl    16.3    16.3     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16399    WarrantyControl    DATABASE     �   CREATE DATABASE "WarrantyControl" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
 !   DROP DATABASE "WarrantyControl";
                postgres    false            �            1259    16413    device    TABLE     �   CREATE TABLE public.device (
    id bigint NOT NULL,
    serial_number character varying(255) NOT NULL,
    brand character varying(255) NOT NULL,
    model character varying(255) NOT NULL
);
    DROP TABLE public.device;
       public         heap    postgres    false            �            1259    16412    device_id_seq    SEQUENCE     �   CREATE SEQUENCE public.device_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.device_id_seq;
       public          postgres    false    216            �           0    0    device_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.device_id_seq OWNED BY public.device.id;
          public          postgres    false    215            �            1259    16420    warranty    TABLE     �   CREATE TABLE public.warranty (
    id bigint NOT NULL,
    device_id bigint NOT NULL,
    purchase_date date NOT NULL,
    warranty_status character varying(255) NOT NULL
);
    DROP TABLE public.warranty;
       public         heap    postgres    false            �            1259    16419    warranty_id_seq    SEQUENCE     �   CREATE SEQUENCE public.warranty_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.warranty_id_seq;
       public          postgres    false    218            �           0    0    warranty_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.warranty_id_seq OWNED BY public.warranty.id;
          public          postgres    false    217                       2604    16431 	   device id    DEFAULT     f   ALTER TABLE ONLY public.device ALTER COLUMN id SET DEFAULT nextval('public.device_id_seq'::regclass);
 8   ALTER TABLE public.device ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216                        2604    16445    warranty id    DEFAULT     j   ALTER TABLE ONLY public.warranty ALTER COLUMN id SET DEFAULT nextval('public.warranty_id_seq'::regclass);
 :   ALTER TABLE public.warranty ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            �          0    16413    device 
   TABLE DATA           A   COPY public.device (id, serial_number, brand, model) FROM stdin;
    public          postgres    false    216   �       �          0    16420    warranty 
   TABLE DATA           Q   COPY public.warranty (id, device_id, purchase_date, warranty_status) FROM stdin;
    public          postgres    false    218   G       �           0    0    device_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.device_id_seq', 23, true);
          public          postgres    false    215            �           0    0    warranty_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.warranty_id_seq', 22, true);
          public          postgres    false    217            "           2606    16433    device device_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.device DROP CONSTRAINT device_pkey;
       public            postgres    false    216            $           2606    16447    warranty warranty_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.warranty
    ADD CONSTRAINT warranty_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.warranty DROP CONSTRAINT warranty_pkey;
       public            postgres    false    218            %           2606    16452    warranty fk_device    FK CONSTRAINT     �   ALTER TABLE ONLY public.warranty
    ADD CONSTRAINT fk_device FOREIGN KEY (device_id) REFERENCES public.device(id) ON DELETE CASCADE;
 <   ALTER TABLE ONLY public.warranty DROP CONSTRAINT fk_device;
       public          postgres    false    4642    218    216            �   y   x�3�t2�4
3��tvp���uuwT020��2�ru726�I�y:*8r�A�Mф- ��0q.#�`$��!T0�##����Ɯ!A�FƆ���y�%�E��IE�
��\1z\\\ V
-      �   U   x�e�1
�0D�z�.���1�������������o�0�9hF��-	�?D�)�T�]����ZeDvTkX�:25��9&y 1n     