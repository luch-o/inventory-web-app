# Generated by Django 3.2.6 on 2021-08-25 21:45

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('products', '0001_initial'),
    ]

    operations = [
        migrations.RenameField(
            model_name='product',
            old_name='code',
            new_name='id',
        ),
    ]