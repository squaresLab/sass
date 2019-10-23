public class Plan1571771425045 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}


}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


}
}
