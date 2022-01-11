# madcamp_pj2_frontend

## Development Environment
- Android studio
  - Java

## App Explanation

```
This is an application that helps people arrange their appointments easily online.
```

## App function description

### Login Tab
```
- Used Kakao Login SDK for login progress.
- Brings user information from Kakao DB such as profile, nickname, age, gender etc.
```

### Select Tab
```
- Select tab helps users participate in or create appointments.
- Users can wheter participate in existing appointments by clicking appointments or add new appointment by adding plus button.
- By longclicking the appointment, users can delete appointment or get out of appointment. 
```

### Detail Tab
```
- Detail tab helps users set detail information of the appointment.
- Users can set title of appointment, appointment days(5 days at most), possible start time and end time of appointment.
- Users can delete and modify detail information by longclicking item or reclicking the buttons.
```

### Time Tab
```
- Time tab helps users set their possible time table and uploads the information to the server.
- Users can check their validility on time table by clicking the toggle button(default: false).
- All toggle buttons are set based on the detail information input through recyclerview layout.
```

### Result Tab
```
- Result tab offers total data of participants participated in the appointment.
- Result tab shows the possible members through color. Brighter colors mean larger possible participants.
- If you click the colors, the toast message tells you the number of possible participants out of all participants at specific time.
```


## Contritbutor
* 정우진 | https://github.com/woojin7879
* 이우진 | https://github.com/woojinnn

