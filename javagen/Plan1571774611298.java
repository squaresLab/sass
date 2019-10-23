public class Plan1571774611298 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
if ( StartServer("C") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}

} else {
StartServer("A");
}


}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("A");
}

}


}
}
