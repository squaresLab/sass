public class Plan1571774460083 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("A");
}

}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


}
}
